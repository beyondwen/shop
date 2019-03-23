package com.wenhao.web.base;

import com.wenhao.base.BaseResponse;
import com.wenhao.constants.Constants;
import org.springframework.ui.Model;

public class BaseWebController {
	/**
	 * 500页面
	 */
	protected static final String ERROR_500_FTL = "500.ftl";

	public Boolean isSuccess(BaseResponse<?> baseResp) {
		if (baseResp == null) {
			return false;
		}
		if (!baseResp.getCode().equals(Constants.HTTP_RES_CODE_200)) {
			return false;
		}
		return true;
	}

	public void setErrorMsg(Model model, String errorMsg) {
		model.addAttribute("error", errorMsg);
	}

}
