package com.yuansfer.payment.request.dataSearch;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.dataSearch.TransListResponse;

import net.sf.json.JSONObject;

public class TransListRequest extends YuanpayRequest<TransListResponse> {

	private String startDate;
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}
	public TransListRequest setStartDate(String startDate) {
		this.startDate = startDate;
		return this;
	}
	public String getEndDate() {
		return endDate;
	}
	public TransListRequest setEndDate(String endDate) {
		this.endDate = endDate;
		return this;
	}
	
	
	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.startDate)) {
			throw new YuanpayException("startDate missing");
		}
		if (StringUtils.isEmpty(this.endDate)) {
			throw new YuanpayException("endDate missing");
		}
		
		try {
			ParamValidator.dateValidate("startDate", this.startDate);
			
			ParamValidator.dateValidate("endDate", this.endDate);
			
			if (Integer.valueOf(this.endDate) < Integer.valueOf(this.startDate)) {
				throw new RuntimeException("endDate should be later than startDate");
			}
		} catch (Exception e) {
			throw new YuanpayException(e.getMessage());
		}
	}
	
	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.TRANS_LIST;
		return url;
	}
	
	@Override
	public TransListResponse convertResponse(String ret) {
		TransListResponse response = new TransListResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("transactions")) {
			response.setTransactions(json.getJSONArray("transactions"));
		}
		if (null != json.get("size")) {
			response.setSize(json.getInt("size"));
		}
		return response;
	}
	
}
