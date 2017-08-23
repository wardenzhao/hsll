package cn.thinkjoy.hsll.controller.admin;

import cn.thinkjoy.hsll.bean.Batch;
import cn.thinkjoy.hsll.bean.Goods;
import cn.thinkjoy.hsll.bean.GoodsSpec;
import cn.thinkjoy.hsll.bean.adminBean.BatchInfo;
import cn.thinkjoy.hsll.bean.adminBean.BatchResponse;
import cn.thinkjoy.hsll.bean.adminBean.GoodsCodeResponse;
import cn.thinkjoy.hsll.bean.adminBean.ResultResponse;
import cn.thinkjoy.hsll.service.BatchService;
import cn.thinkjoy.hsll.service.GoodsService;
import cn.thinkjoy.hsll.service.GoodsSpecService;
import cn.thinkjoy.hsll.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by wpliu on 17/8/20.
 */
@Scope("prototype")
@Controller("batchAdminController")
@RequestMapping(value = "/admin/batch")
public class BatchAdminController {
    @Autowired
    private BatchService batchService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsSpecService goodsSpecService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public BatchResponse list(HttpServletRequest request,HttpServletResponse response,int pageNo,int pageSize){
        BatchResponse batchResponse=new BatchResponse();
        List<BatchInfo>  batches=batchService.getBatchList(pageNo*pageSize,pageSize);
        int count =batchService.getBatchCount();
        for(int i=0;i<batches.size();i++){
            BatchInfo batchInfo=batches.get(i);
            GoodsSpec goodsSpec= goodsSpecService.getSpecById(Long.valueOf(batchInfo.getGoodSpecId()));
            Goods goods=goodsService.getGoodsById(goodsSpec.getGoodsId());
            int useNum =batchService.getUseNumByBatchCode(batchInfo.getBatchCode());
            batchInfo.setGoodsName(goods.getName());
            batchInfo.setGoodSpecName(goodsSpec.getSpecName());
            batchInfo.setUsedNum(useNum);
        }
        batchResponse.setBatchList(batches);
        batchResponse.setPageNo(pageNo);
        batchResponse.setListCount(count);
        return batchResponse;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse add(HttpServletRequest request,HttpServletResponse response,String name,String specId,String goodsId,int number){
        ResultResponse result=new ResultResponse();
        String batchCode=System.currentTimeMillis()+"";//批次代码
        Date date=new Date();
        try{
            for(int i=0;i<number;i++){
                Batch batch=new Batch();
                batch.setBatchCode(batchCode);
                batch.setBatchName(name);
                batch.setGoodSpecId(Long.valueOf(specId));
                batch.setCreatedTime(date);
                batch.setGoodsCode(UuidUtil.generateShortUuid());
                batch.setBatchNo(i+1+"");
                batchService.add(batch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setRet(0);
        result.setMsg("success");
        return result;
    }

    /**
     * 获取商品列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "getGoodsInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<Goods> getGoodsInfo(HttpServletRequest request,HttpServletResponse response){
        List<Goods> goodsList=goodsService.getGoodsList();
        return goodsList;
    }

    /**
     * 获取商品规格
     * @param request
     * @param response
     * @param goodId
     * @return
     */
    @RequestMapping(value = "getGoodSpec",method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsSpec> getGoodSpecByGoodId(HttpServletRequest request,HttpServletResponse response,long goodId){
        return  goodsSpecService.getSpecLsBygoodId(goodId);
    }

    /**
     * 查看提货码
     * @param request
     * @param response
     * @param batchCode
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "viewGoodsCode",method = RequestMethod.GET)
    @ResponseBody
    public GoodsCodeResponse viewGoodsCode(HttpServletRequest request,HttpServletResponse response,String batchCode,int pageNo,int pageSize){
         List<BatchInfo>  goodCodeList=batchService.getGoodCodeList(batchCode, pageNo * pageSize, pageSize);
        GoodsCodeResponse goodsCodeResponse=new GoodsCodeResponse();
        for(int i=0;i<goodCodeList.size();i++){
            BatchInfo batchInfo=goodCodeList.get(i);
            GoodsSpec goodsSpec= goodsSpecService.getSpecById(Long.valueOf(batchInfo.getGoodSpecId()));
            Goods goods=goodsService.getGoodsById(goodsSpec.getGoodsId());
            batchInfo.setGoodsName(goods.getName());
            batchInfo.setGoodSpecName(goodsSpec.getSpecName());
        }
        int count=batchService.getUnUseNumByBatchCode(batchCode);
        return goodsCodeResponse;
    }


}
