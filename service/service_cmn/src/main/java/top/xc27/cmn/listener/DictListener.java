package top.xc27.cmn.listener;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import top.xc27.cmn.dao.DictDao;
import top.xc27.model.dict.DictEntity;
import top.xc27.vo.DictEeVo;

public class DictListener extends AnalysisEventListener<DictEeVo> {

    private DictDao dictMapper;

    public DictListener(DictDao dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        DictEntity dict = new DictEntity();
        BeanUtil.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
