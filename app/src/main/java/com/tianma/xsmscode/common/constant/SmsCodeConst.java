package com.tianma.xsmscode.common.constant;

/**
 * SmsCode Constant
 */
public interface SmsCodeConst {

    String[] VERIFICATION_KEY_WORDS_CN = {
            "验证码"   /**/,
            "校验码"   /**/,
            "检验码"   /**/,
            "确认码"   /**/,
            "激活码"   /**/,
            "动态码"   /**/,

            "验证代码" /**/,
            "校验代码" /**/,
            "检验代码" /**/,
            "激活代码" /**/,
            "确认代码" /**/,
            "动态代码" /**/,

            "短信口令" /**/,
            "动态密码" /**/,
            "交易码"   /**/,
            "上网密码" /**/,

            "驗證碼"   /**/,
            "校驗碼"   /**/,
            "檢驗碼"   /**/,
            "確認碼"   /**/,
            "激活碼"   /**/,
            "動態碼"   /**/,

            "驗證代碼" /**/,
            "校驗代碼" /**/,
            "檢驗代碼" /**/,
            "確認代碼" /**/,
            "激活代碼" /**/,
            "動態代碼" /**/,
    };

    String VERIFICATION_KEYWORDS_REGEX =
            /**/   "验证码|校验码|检验码|确认码|激活码|动态码|安全码" +
            /**/  "|验证代码|校验代码|检验代码|激活代码|确认代码|动态代码|安全代码" +
            /**/  "|登入码|认证码|识别码" +
            /**/  "|短信口令|动态密码|交易码|上网密码|随机码|动态口令" +
            /**/  "|驗證碼|校驗碼|檢驗碼|確認碼|激活碼|動態碼" +
            /**/  "|驗證代碼|校驗代碼|檢驗代碼|確認代碼|激活代碼|動態代碼" +
            /**/  "|登入碼|認證碼|識別碼" +
            /**/  "|Code|code|CODE";

    String[] VERIFICATION_KEY_WORDS_EN = {
            "Code",
            "code",
            "CODE",
    };

    String PHONE_NUMBER_KEYWORDS =
            /**/ "手机号|电话号" +
            /**/ "|手機號|電話號" +
            /*(?i) 表示忽略大小写*/ "|(?i)phone(?-i)" +
            /*(?i) 表示忽略大小写*/ "|(?i)number(?-i)";
}
