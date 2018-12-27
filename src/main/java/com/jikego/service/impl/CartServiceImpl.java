package com.jikego.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.jikego.common.Const;
import com.jikego.common.ResponseCode;
import com.jikego.common.ServerResponse;
import com.jikego.dao.CartMapper;
import com.jikego.dao.ProductMapper;
import com.jikego.pojo.Cart;
import com.jikego.pojo.Product;
import com.jikego.service.ICartService;
import com.jikego.util.BigDecimalUtil;
import com.jikego.util.PropertiesUtil;
import com.jikego.vo.CartProductVo;
import com.jikego.vo.CartVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author Geekerstar(jikewenku.com)
 * Date: 2018/6/24 8:49
 * Description:
 */

@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;


    /**
     * description: 添加购物车
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:22
     * param: [userId, productId, count]
     * return: com.jikego.common.ServerResponse<com.jikego.vo.CartVo>
     */
    @Override
    public ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count) {
        if (productId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart == null) {
            //这个产品不在这个购物车里,需要新增一个这个产品的记录
            Cart cartItem = new Cart();
            cartItem.setQuantity(count);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setProductId(productId);
            cartItem.setUserId(userId);
            cartMapper.insert(cartItem);
        } else {
            //这个产品已经在购物车里了
            //如果产品已存在,数量相加
            count = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }

    /**
     * description: 更新购物车
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:22
     * param: [userId, productId, count]
     * return: com.jikego.common.ServerResponse<com.jikego.vo.CartVo>
     */
    @Override
    public ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count) {
        if (productId == null || count == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId, productId);
        if (cart != null) {
            cart.setQuantity(count);
        }
        cartMapper.updateByPrimaryKey(cart);
        return this.list(userId);
    }

    /**
     * description: 购物车中删除商品
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:22
     * param: [userId, productIds]
     * return: com.jikego.common.ServerResponse<com.jikego.vo.CartVo>
     */
    @Override
    public ServerResponse<CartVo> deleteProduct(Integer userId, String productIds) {
        List<String> productList = Splitter.on(",").splitToList(productIds);
        if (CollectionUtils.isEmpty(productList)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        cartMapper.deleteByUserIdProductIds(userId, productList);
        return this.list(userId);
    }

    /**
     * description: 查询
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:23
     * param: [userId]
     * return: com.jikego.common.ServerResponse<com.jikego.vo.CartVo>
     */
    @Override
    public ServerResponse<CartVo> list(Integer userId) {
        CartVo cartVo = this.getCartVoLimit(userId);
        return ServerResponse.createBySuccess(cartVo);
    }

    /**
     * description: 选择或者反选所有
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:23
     * param: [userId, productId, checked]
     * return: com.jikego.common.ServerResponse<com.jikego.vo.CartVo>
     */
    @Override
    public ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked) {
        cartMapper.checkedOrUncheckedProduct(userId, productId, checked);
        return this.list(userId);
    }

    /**
     * description: 获取购物车中产品数量
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:23
     * param: [userId]
     * return: com.jikego.common.ServerResponse<java.lang.Integer>
     */
    @Override
    public ServerResponse<Integer> getCartProductCount(Integer userId) {
        if (userId == null) {
            return ServerResponse.createBySuccess(0);
        }
        return ServerResponse.createBySuccess(cartMapper.selectCartProductCount(userId));
    }

    /**
     * description: 获取库存
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:24
     * param: [userId]
     * return: com.jikego.vo.CartVo
     */
    private CartVo getCartVoLimit(Integer userId) {
        CartVo cartVo = new CartVo();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartProductVo> cartProductVoList = Lists.newArrayList();

        BigDecimal cartTotalPrice = new BigDecimal("0");

        if (CollectionUtils.isNotEmpty(cartList)) {
            for (Cart cartItem : cartList) {
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setId(cartItem.getId());
                cartProductVo.setUserId(userId);
                cartProductVo.setProductId(cartItem.getProductId());

                Product product = productMapper.selectByPrimaryKey(cartItem.getProductId());
                if (product != null) {
                    cartProductVo.setProductMainImage(product.getMainImage());
                    cartProductVo.setProductName(product.getName());
                    cartProductVo.setProductSubtitle(product.getSubtitle());
                    cartProductVo.setProductStatus(product.getStatus());
                    cartProductVo.setProductPrice(product.getPrice());
                    cartProductVo.setProductStock(product.getStock());
                    //判断库存
                    int buyLimitCount = 0;
                    if (product.getStock() >= cartItem.getQuantity()) {
                        //库存充足的时候
                        buyLimitCount = cartItem.getQuantity();
                        cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                    } else {
                        buyLimitCount = product.getStock();
                        cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                        //购物车中更新有效库存
                        Cart cartForQuantity = new Cart();
                        cartForQuantity.setId(cartItem.getId());
                        cartForQuantity.setQuantity(buyLimitCount);
                        cartMapper.updateByPrimaryKeySelective(cartForQuantity);
                    }
                    cartProductVo.setQuantity(buyLimitCount);
                    //计算总价
                    cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(), cartProductVo.getQuantity()));
                    cartProductVo.setProductChecked(cartItem.getChecked());
                }
                //如果不判断是否有商品就进行添加购物车操作，会报空指针异常。
                if (cartItem.getChecked() == Const.Cart.CHECKED) {
                    //如果已经勾选,增加到整个的购物车总价中
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartProductVoList.add(cartProductVo);
            }
        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));
        cartVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        return cartVo;
    }

    /**
     * description: 返回选择状态
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:24
     * param: [userId]
     * return: boolean
     */
    private boolean getAllCheckedStatus(Integer userId) {
        if (userId == null) {
            return false;
        }
        return cartMapper.selectCartProductCheckedStatusByUserId(userId) == 0;

    }
}
