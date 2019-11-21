package com.agricluture.login;

public class LoginBean {

    private static LoginBean loginbean = null;

    /**
     * id : 11D08F00-AE90-47A8-BB93-47F1FA4D5123
     * intconfidentiallevel : 1
     * timlogintime : 2019-10-24T16:31:40.000+0000
     * strRealName : 数据采集员6
     * organizationId : 08de6316-f497-453c-b301-e4b5d45b120a
     * organizationNO : 0
     */

    private String id;
    private int intconfidentiallevel;
    private String timlogintime;
    private String strRealName;
    private String organizationId;
    private String organizationNO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIntconfidentiallevel() {
        return intconfidentiallevel;
    }

    public void setIntconfidentiallevel(int intconfidentiallevel) {
        this.intconfidentiallevel = intconfidentiallevel;
    }

    public String getTimlogintime() {
        return timlogintime;
    }

    public void setTimlogintime(String timlogintime) {
        this.timlogintime = timlogintime;
    }

    public String getStrRealName() {
        return strRealName;
    }

    public void setStrRealName(String strRealName) {
        this.strRealName = strRealName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationNO() {
        return organizationNO;
    }

    public void setOrganizationNO(String organizationNO) {
        this.organizationNO = organizationNO;
    }
    public static LoginBean getInstance() {
        if (loginbean == null) {
            synchronized (LoginBean.class) {
                if (loginbean == null) {
                    loginbean = new LoginBean();
                }
            }
        }
        return loginbean;
    }
}
