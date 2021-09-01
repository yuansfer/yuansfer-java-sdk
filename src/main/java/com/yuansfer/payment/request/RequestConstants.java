package com.yuansfer.payment.request;

public interface RequestConstants {
	
	/*
	 * 网关
	 */
	String SANDBOX_PREFIX = "https://mapi.yuansfer.yunkeguan.com";
	String PRODUCT_PREFIX = "https://mapi.yuansfer.com";
	
	/*
	 * online接口后缀
	 */
	String ONLINE_SECUREPAY = "/online/v3/secure-pay";
	String UPDATE_RECURRING = "/creditpay/v3/update-recurring";
	String PROCESS = "/creditpay/v3/process";
	/*
	 * instore接口后缀
	 */
	String INSTORE_ADD = "/app-instore/v3/add";
	@Deprecated String INSTORE_PAY = "/app-instore/v3/pay";
	String INSTORE_PREPAY = "/app-instore/v3/prepay";
	String INSTORE_TRAN_QRCODE = "/app-instore/v3/create-trans-qrcode";
	String INSTORE_CASHIER_ADD = "/app-instore/v3/cashier-add";
	String INSTORE_AUTH_CAPTURE = "/app-instore/v3/auth-capture";
	String INSTORE_AUTH_UNFREEZE = "/app-instore/v3/auth-unfreeze";
	
	/*
	 * mobile接口后缀
	 */
	String MOBILE_PREPAY = "/micropay/v3/prepay";
	String EXPRESS_PAY = "/micropay/v3/express-pay";
	
	/*
	 * data-search
	 */
	String REFUND = "/app-data-search/v3/refund";
	String REVERSE = "/app-data-search/v3/reverse";
	String TRAN_QUERY = "/app-data-search/v3/tran-query";
	String TRANS_LIST = "/app-data-search/v3/trans-list";
	String SETTLE_LIST = "/app-data-search/v3/settle-list";
	String WITHDRAWAL_LIST = "/app-data-search/v3/withdrawal-list";
	String DATA_STATUS = "/app-data-search/v3/data-status";
	
	
	/*
	 * 预授权
	 */
	String AUTH_VOUCHER_CREATE = "/app-auth/v3/voucher-create";
	String AUTH_FREEAE = "/app-auth/v3/auth-freeze";
	String AUTH_UNFREEZE = "/app-auth/v3/auth-unfreeze";
	String AUTH_CAPTURE = "/app-auth/v3/auth-capture";
	String AUTH_DETAIL_QUERY = "/app-auth/v3/auth-detail-query";
	
	/*
	 * 第三方授权支付
	 */
	String THIRDPART_ACQUIRE_CREATE = "/app-thirdpart/v3/acquire-create";
	
	
	/*
	 * auto debit
	 */
	String AUTO_CONSULT = "/auto-debit/v3/consult";
	String AUTO_APPLY_TOKEN = "/auto-debit/v3/apply-token";
	String AUTO_PAY = "/auto-debit/v3/pay";
	String AUTO_REVOKE = "/auto-debit/v3/revoke";
	
	/*
	 * payouts
	 */
	String PAYOUTS_PAY = "/v3/payouts/pay";
	String PAYOUTS_INQUIRY = "/v3/payouts/inquiry";
}
