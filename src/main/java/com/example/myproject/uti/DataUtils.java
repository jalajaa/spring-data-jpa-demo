package com.example.myproject.util;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

import com.example.myproject.domain.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service 
public class DataUtils{

private static final Logger LOGGER=LoggerFactory.getLogger(DataUtils.class);

     public Map listToMap(List<Page> pageList){
        LOGGER.info("Entering listToMap");
        Map<String, Page> map = new HashMap<>();
  
        // put every value list to Map
        for (Page page : pageList) {
            map.put(page.getId() + "", page);
        }
  
        // print map
        LOGGER.info("Map  : {}",map);
        LOGGER.info("Leaving listToMap");
        return map;
        
    }
    
    public ArrayList<Page> mapToList( Map<String, Page> map){
        LOGGER.info("Entering mapToList");
        
        
         // Using Collection
        Collection<Page> val = map.values();
 
        // Creating an ArrayList
        ArrayList<Page> pageList = new ArrayList<Page>(val);
  
        // print List
        LOGGER.info("pageList  : {}",pageList);
        LOGGER.info("Leaving mapToList");
        return pageList;
        
    }
}