package com.example.demo.service;

import com.example.demo.MoneyInfo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@AllArgsConstructor
@Log4j2
public class ClientHttp {

    public MoneyInfo convertHanShengToMoneyInfo() {
        MoneyInfo moneyInfo = new MoneyInfo ();
        moneyInfo.setCompany ("HanSheng");
        try {
            Document doc = Jsoup.connect ("http://www.hanshanmoney.com/zh-cn/ratecn/").get ();
            Elements links = doc.select ("font[color*=\"#000080\"]");
            moneyInfo.setRate (links.first ().childNode (0).toString ());
        } catch (IOException e) {
            log.error (e.getMessage ());
            e.printStackTrace ();
        }
        return moneyInfo;
    }

    public MoneyInfo convertChangeChengToMoneyInfo() {
        MoneyInfo moneyInfo = new MoneyInfo ();
        moneyInfo.setCompany ("ChangCheng");
        try {
            Document doc = Jsoup.connect ("http://www.zhongguoremittance.com/zh-hans/").get ();
            Elements links = doc.select ("div[class=\"rate\"]");
            moneyInfo.setRate (links.first ().childNode (5).childNode (0).toString ());
        } catch (IOException e) {
            log.error (e.getMessage ());
            e.printStackTrace ();
        }
        return moneyInfo;
    }
}
