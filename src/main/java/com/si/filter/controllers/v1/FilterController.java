package com.si.filter.controllers.v1;

import com.si.filter.services.IFilterService;
import com.si.filter.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(Constants.FILTER_BASE_URL)
public class FilterController {

    private final IFilterService filterService;

    @Autowired
    public FilterController(IFilterService filterService) {
        this.filterService = filterService;
    }

    @RequestMapping(method = {RequestMethod.POST})
    public ResponseEntity<String> filterRequest(@RequestBody String body) {
        return new ResponseEntity(filterService.filterPermissions(body), HttpStatus.OK);
    }
}
