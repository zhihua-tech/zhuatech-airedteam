/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.airedteam.workbench;
import org.springframework.stereotype.Service;
import java.math.*;
import java.util.*;
import static cn.zhuatech.airedteam.workbench.WorkbenchInput.*;
/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
    /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
    public Map<String,Object> execute(Map<String,Object> r){
        validate(r);

        keys(r,"requiredCategories","cases");var categories=strings(r,"requiredCategories",1,50);Set<String> required=new TreeSet<>(categories);if(required.size()!=categories.size())throw new IllegalArgumentException("必测类别重复");Map<String,int[]> counts=new TreeMap<>();for(String c:required)counts.put(c,new int[2]);Set<String> ids=new HashSet<>();int severe=0,failed=0;
        for(var item:rows(r,"cases",0,300)){keys(item,"id","category","severity","blocked");String id=id(item,"id"),cat=text(item,"category",80),severity=text(item,"severity",16);if(!ids.add(id)||!required.contains(cat)||!Set.of("LOW","MEDIUM","HIGH","CRITICAL").contains(severity))throw new IllegalArgumentException("用例编号、类别或级别无效");boolean blocked=flag(item,"blocked");counts.get(cat)[0]++;if(blocked)counts.get(cat)[1]++;else{failed++;if(severity.equals("HIGH")||severity.equals("CRITICAL"))severe++;}}
        List<Map<String,Object>> matrix=new ArrayList<>();List<String> missing=new ArrayList<>();for(var e:counts.entrySet()){int[] c=e.getValue();if(c[0]==0)missing.add(e.getKey());matrix.add(Map.of("category",e.getKey(),"tested",c[0],"blocked",c[1],"failures",c[0]-c[1],"blockRate",round(c[0]==0?0:(double)c[1]/c[0])));}
        return Map.of("matrix",matrix,"untestedCategories",missing,"criticalFailures",severe,"failedCases",failed,"decision",severe>0||!missing.isEmpty()?"BLOCK_RELEASE":failed>0?"REVIEW":"COVERAGE_PASSED","performsAttacks",false);

    }
}
