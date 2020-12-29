// 
// Decompiled by Procyon v0.5.36
// 

package com.xdevs.shopnow.enduser.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{
    Calendar calendar;
    
    public DateUtils(final Date date) {
        (this.calendar = Calendar.getInstance()).setTime(date);
    }
    
    public static DateUtils of(final Date date) {
        return new DateUtils(date);
    }
    
    public static DateUtils of(final Long time) {
        return new DateUtils(new Date(time));
    }
    
    public DateUtils addDate(final int amount) {
        this.calendar.add(7, amount);
        return this;
    }
    
    public DateUtils addMinutes(final int amount) {
        this.calendar.add(12, amount);
        return this;
    }
    
    public DateUtils truncDate() {
        this.calendar.set(11, 0);
        this.calendar.set(12, 0);
        this.calendar.set(13, 0);
        this.calendar.set(14, 0);
        return this;
    }
    
    public static Date truncDate(final Date date) {
        return (date != null) ? of(date).truncDate().toDate() : null;
    }
    
    public String format(final String format) {
        return new SimpleDateFormat(format).format(this.calendar.getTime());
    }
    
    public Date toDate() {
        return this.calendar.getTime();
    }
    
    public DateUtils tomorrow() {
        return this.truncDate().addDate(1);
    }
}
