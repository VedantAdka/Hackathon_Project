package com.cts.policy_bazaar.testrunner;

import com.cts.policy_bazaar.browserutils.BrowserFactory;
import com.cts.policy_bazaar.frameworkutils.PropertiesFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class hahah {
    WebDriver driver=null;
    @Test
    public void run() throws Exception {
        String bn = PropertiesFileReader.getPropertyValue("config", "browsername");
        String wr = PropertiesFileReader.getPropertyValue("config", "wheretorun");
        String remoteip = PropertiesFileReader.getPropertyValue("config", "hubip");
        String url = PropertiesFileReader.getPropertyValue("config", "url");

        driver = BrowserFactory.getBrowser(bn, wr, remoteip);
        BrowserFactory.OpenUrl("https://travel.policybazaar.com/quotes?encp=RlJ0bmVUOGE0LzRGQkRRUlY3VXY4UT09&isPlanCTAExp=1&isRepeatMember=0&newpq=1&token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE3NTI4Mjc4MTcsImp0aSI6IjhjYTFiNGVjLWU1YTQtNGE2Zi1iZDdjLTY3OGMyMmVlNzE1OCIsIlByb3Bvc2VySUQiOiI0NzU2MzQ1IiwibmJmIjoxNzUyODI3ODE3LCJleHAiOjE3NTU0NTU4MTcsImlzcyI6InRyYXZlbC5wb2xpY3liYXphYXIuY29tIiwiYXVkIjoidHJhdmVsIn0.Nx_ogRnorAuplDlkaALYgQSWPOD_1WYg3AeyQjt-GAuGAxf1ui0Igu6yExBBvmCpe0ET_vyVdX51sOKEKzYQUlsy9z1N5UNS6lHanKursgvH8NmpvTHJuM0hVoQmIgVdvE1_8qx_vU2i4WrjmuMvjr79xQD2RHgx7ozgT26CY5zGeMxL1E3m7VB6mCRZ_Btt6b00Gzd3pxqyRiqNmtHOeXUsEq4E5PphfbbvMmI8zXFflTlfCKX_5EUJpUgOEADj3zx97WsxsMf1xT7Xlc3NGk7EpK6Hh-VvVdOSJHWw0ohUsF3NON5K0bjJNTJAV5_t7Xqe4SKB853t9RvmwH_VqQ&utm_content=newpq&utm_term=newjourney");
        driver.findElement(By.xpath("//input[@id='studentTrip' and @name='filter_otherPlans']")).click();

    }
}
