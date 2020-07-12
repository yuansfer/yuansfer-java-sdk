package com.yuanex.payment.request;

public interface RequestConstants {
	/*
	 * 网关
	 */
	String SANDBOX_PREFIX = "https://mapi.yuansfer.yunkeguan.com";
	String PRODUCT_PREFIX = "https://mapi.yuansfer.com";
	
	/*
	 * online接口后缀
	 */
	String ONLINE_SECUREPAY = "/online/v2/secure-pay";
	String UPDATE_RECURRING = "/creditpay/v2/update-recurring";
	
	/*
	 * instore接口后缀
	 */
	String INSTORE_ADD = "/app-instore/v2/add";
	String INSTORE_PAY = "/app-instore/v2/pay";
	String INSTORE_TRAN_QRCODE = "/app-instore/v2/create-trans-qrcode";
	String INSTORE_CASHIER_ADD = "/app-instore/v2/cashier-add";
	String INSTORE_AUTH_CAPTURE = "/app-instore/v2/auth-capture";
	String INSTORE_AUTH_UNFREEZE = "/app-instore/v2/auth-unfreeze";
	
	/*
	 * mobile接口后缀
	 */
	String MOBILE_PREPAY = "/micropay/v2/prepay";
	String EXPRESS_PAY = "/micropay/v2/express-pay";
	
	/*
	 * data-search
	 */
	String REFUND = "/app-data-search/v2/refund";
	String REVERSE = "/app-data-search/v2/reverse";
	String TRAN_QUERY = "/app-data-search/v2/tran-query";
	String TRANS_LIST = "/app-data-search/v2/trans-list";
	String SETTLE_LIST = "/app-data-search/v2/settle-list";
	String WITHDRAWAL_LIST = "/app-data-search/v2/withdrawal-list";
	String DATA_STATUS = "/app-data-search/v2/data-status";
	
	
	/*
	 * 预授权
	 */
	String AUTH_VOUCHER_CREATE = "/app-auth/v2/voucher-create";
	String AUTH_FREEAE = "/app-auth/v2/auth-freeze";
	String AUTH_UNFREEZE = "/app-auth/v2/auth-unfreeze";
	String AUTH_CAPTURE = "/app-auth/v2/auth-capture";
	String AUTH_DETAIL_QUERY = "/app-auth/v2/auth-detail-query";
	
	/*
	 * 第三方授权支付
	 */
	String THIRDPART_ACQUIRE_CREATE = "/app-thirdpart/v2/acquire-create";
	
}
