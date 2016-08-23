package org.ssa.ironyard.web;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DateController
{
    @RequestMapping("/date")
    @ResponseBody
    public String date() throws ParseException
    {
        DateFormatter date = new DateFormatter();
        return date.parse("Aug 26, 2016", Locale.US).toString();

    }

}
