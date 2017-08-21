package cn.thinkjoy.hsll.controller.admin;

import cn.thinkjoy.hsll.bean.Goods;
import cn.thinkjoy.hsll.bean.GoodsSpec;
import cn.thinkjoy.hsll.bean.adminBean.ProductRequest;
import cn.thinkjoy.hsll.bean.adminBean.ProductResponse;
import cn.thinkjoy.hsll.bean.adminBean.ProductSpec;
import cn.thinkjoy.hsll.bean.adminBean.ResultResponse;
import cn.thinkjoy.hsll.service.GoodsService;
import cn.thinkjoy.hsll.service.GoodsSpecService;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpliu on 17/8/13.
 * 后台管理控制类
 */
@Scope("prototype")
@Controller("adminController")
@RequestMapping(value = "/admin/product")
public class ProductController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsSpecService goodsSpecService;

    /**
     * 商品列别
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<ProductResponse> list(HttpServletRequest request,HttpServletResponse response){
        List<ProductResponse> productResponses=new ArrayList<>();
        List<Goods> goodsList=goodsService.getGoodsList();
        if(goodsList!=null&&goodsList.size()>0){
            for(Goods good:goodsList){
                ProductResponse productResponse=new ProductResponse();
                productResponse.setProductId(good.getId());
                productResponse.setProductName(good.getName());
                productResponse.setTitle(good.getTitle());
                productResponse.setImages(good.getImage());
                productResponse.setSubTitle(good.getSubTitle());
                List<GoodsSpec> goodsSpecList= goodsSpecService.getSpecLsBygoodId(good.getId());
                List<ProductSpec> productSpecs=new ArrayList<>();
                if(goodsSpecList!=null&&goodsSpecList.size()>0){
                    for (GoodsSpec goodsSpec:goodsSpecList){
                        ProductSpec productSpec=new ProductSpec();
                        productSpec.setSpecId(goodsSpec.getId());
                        productSpec.setSpecName(goodsSpec.getSpecName());
                        productSpec.setSpecPrice(goodsSpec.getPrice()+"");
                        productSpec.setSpecStatus(goodsSpec.getStatus()+"");
                        productSpecs.add(productSpec);
                    }
                }
                productResponse.setSpecs(productSpecs);
                productResponses.add(productResponse);
            }
        }
        return productResponses;
    }


    /**
     * 更新商品
     * @param request
     * @param response
     * @param productRequest
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse update(HttpServletRequest request,HttpServletResponse response,ProductRequest productRequest){
        ResultResponse result=new ResultResponse();
        try{
            String specsJson=productRequest.getSpecs();
            JSONArray jsonMap=(JSONArray)JSON.parse(specsJson);
            for(int i=0;i<jsonMap.length();i++){
              JSONObject map=(JSONObject) jsonMap.get(i);
               ProductSpec productSpec= new ProductSpec();
                productSpec.setSpecId((Long)map.get("specId"));
                productSpec.setSpecName(map.getString("specName"));
                productSpec.setSpecPrice(map.getString("specPrice"));
                productSpec.setSpecStatus(map.getString("specStatus"));
                goodsSpecService.update(productSpec);
            }
            goodsService.updateGood(productRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
        result.setRet(0);
        result.setMsg("success");
        return result;
    }

    /**
     * 添加商品
     * @param request
     * @param response
     * @param productRequest
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse add(HttpServletRequest request,HttpServletResponse response,ProductRequest productRequest){
        ResultResponse result=new ResultResponse();
        try{
            String specsJson=productRequest.getSpecs();
            JSONArray jsonMap=(JSONArray)JSON.parse(specsJson);
           long goodId= goodsService.addGood(productRequest);
            for(int i=0;i<jsonMap.length();i++){
                JSONObject map=(JSONObject) jsonMap.get(i);
                ProductSpec productSpec= new ProductSpec();
                productSpec.setGoodId(goodId);
                productSpec.setSpecId((Long) map.get("specId"));
                productSpec.setSpecName(map.getString("specName"));
                productSpec.setSpecPrice(map.getString("specPrice"));
                productSpec.setSpecStatus(map.getString("specStatus"));
                productSpec.setAddress(map.getString("address"));
                goodsSpecService.add(productSpec);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setRet(0);
        result.setMsg("success");
        return result;
    }

    /**
     * 删除商品
     * @param request
     * @param response
     * @param goodId
     * @return
     */
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse del(HttpServletRequest request,HttpServletResponse response,long goodId){
        ResultResponse result=new ResultResponse();
        try{

            goodsService.delGood(goodId);

        }catch (Exception e){
            e.printStackTrace();
        }
        result.setRet(0);
        result.setMsg("success");
        return result;
    }

    /**
     * 商品上下架
     * @param request
     * @param response
     * @param productRequest
     * @return
     */
    @RequestMapping(value = "/upOrDown",method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse upOrDownGood(HttpServletRequest request,HttpServletResponse response,ProductRequest productRequest){
        ResultResponse result=new ResultResponse();
        try{

            goodsService.updateGood(productRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
        result.setRet(0);
        result.setMsg("success");
        return result;
    }


}
