package com.capgemini.capstore.dao;

import com.capgemini.capstore.bean.MerchantTemporaryBean;

public interface MerchantDao {

	public boolean registerAsMerchant(MerchantTemporaryBean merchantTemporaryBean);
}
