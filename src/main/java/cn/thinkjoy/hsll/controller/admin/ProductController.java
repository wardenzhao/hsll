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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpliu on 17/8/13.
 * ��̨���������
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
     * ��Ʒ�б�
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
                productResponse.setDescribe(good.getDescribe());
                List<GoodsSpec> goodsSpecList= goodsSpecService.getSpecLsBygoodId(good.getId());
                List<ProductSpec> productSpecs=new ArrayList<>();
                if(goodsSpecList!=null&&goodsSpecList.size()>0){
                    for (GoodsSpec goodsSpec:goodsSpecList){
                        ProductSpec productSpec=new ProductSpec();
                        productSpec.setSpecId(goodsSpec.getId());
                        productSpec.setSpecName(goodsSpec.getSpecName());
                        productSpec.setSpecPrice(goodsSpec.getPrice() + "");
                        productSpec.setSpecStatus(goodsSpec.getStatus() + "");
                        productSpec.setAddress(goodsSpec.getAddress());
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
     * ��Ʒ�б�
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    public ProductResponse detail(HttpServletRequest request,HttpServletResponse response,long id){
        ProductResponse productResponse=new ProductResponse();
        Goods goods= goodsService.getGoodsById(id);
        List<GoodsSpec> goodsSpecList= goodsSpecService.getSpecLsBygoodId(goods.getId());
        List<ProductSpec> productSpecs=new ArrayList<>();
        if(goodsSpecList!=null&&goodsSpecList.size()>0){
            for (GoodsSpec goodsSpec:goodsSpecList){
                ProductSpec productSpec=new ProductSpec();
                productSpec.setSpecId(goodsSpec.getId());
                productSpec.setSpecName(goodsSpec.getSpecName());
                productSpec.setSpecPrice(goodsSpec.getPrice()+"");
                productSpec.setSpecStatus(goodsSpec.getStatus()+"");
                productSpec.setAddress(goodsSpec.getAddress());
                productSpecs.add(productSpec);
            }
        }
        productResponse.setSpecs(productSpecs);
        productResponse.setProductId(goods.getId());
        productResponse.setProductName(goods.getName());
        productResponse.setTitle(goods.getTitle());
        productResponse.setImages(goods.getImage());
        productResponse.setSubTitle(goods.getSubTitle());
        productResponse.setDescribe(goods.getDescribe());
        return productResponse;
    }
    /**
     * ������Ʒ
     * @param request
     * @param response
     * @param productRequest
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse update(HttpServletRequest request,HttpServletResponse response,@RequestBody ProductRequest productRequest){
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
     * �����Ʒ
     * @param request
     * @param response
     * @param productRequest
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse add(HttpServletRequest request,HttpServletResponse response,@RequestBody ProductRequest productRequest){
        ResultResponse result=new ResultResponse();
        try{
            String specsJson=productRequest.getSpecs();
            JSONArray jsonMap=(JSONArray)JSON.parse(specsJson);
            goodsService.addGood(productRequest);

            for(int i=0;i<jsonMap.length();i++){
                JSONObject map=(JSONObject) jsonMap.get(i);
                ProductSpec productSpec= new ProductSpec();
                productSpec.setGoodId(productRequest.getId());
                productSpec.setSpecName(map.getString("specName"));
                productSpec.setSpecPrice(map.getString("specPrice"));
                productSpec.setAddress(productRequest.getAddress());

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
     * ɾ����Ʒ
     * @param request
     * @param response
     * @param goodId
     * @return
     */
    @RequestMapping(value = "/del",method = RequestMethod.POST)
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
     * ��Ʒ���¼�
     * @param request
     * @param response
     * @param productRequest
     * @return
     */
    @RequestMapping(value = "/upOrDown",method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse upOrDownGood(HttpServletRequest request,HttpServletResponse response,@RequestBody ProductRequest productRequest){
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
