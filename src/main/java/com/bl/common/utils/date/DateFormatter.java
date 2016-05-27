package com.bl.common.utils.date;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date>{

	@Override
	public String print(Date date, Locale locale) {

		return DateUtil.convertDateToStr(date, DateUtil.DEFAULT_DATE_TIME_FORMAT);
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
