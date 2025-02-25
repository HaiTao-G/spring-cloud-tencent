/*
 * Tencent is pleased to support the open source community by making Spring Cloud Tencent available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.tencent.cloud.polaris.contract.config;

import java.util.List;

import com.tencent.cloud.common.constant.OrderConstant;
import com.tencent.cloud.polaris.context.PolarisConfigModifier;
import com.tencent.polaris.factory.config.ConfigurationImpl;
import com.tencent.polaris.factory.config.provider.RegisterConfigImpl;

/**
 * Modifier of service contract.
 *
 * @author Haotian Zhang
 */
public class PolarisContractModifier implements PolarisConfigModifier {

	private final PolarisContractProperties polarisContractProperties;

	public PolarisContractModifier(PolarisContractProperties polarisContractProperties) {
		this.polarisContractProperties = polarisContractProperties;
	}

	@Override
	public void modify(ConfigurationImpl configuration) {
		List<RegisterConfigImpl> registerConfigs = configuration.getProvider().getRegisters();
		for (RegisterConfigImpl registerConfig : registerConfigs) {
			registerConfig.setReportServiceContractEnable(
					polarisContractProperties.isEnabled() && polarisContractProperties.isReportEnabled());
		}
	}

	@Override
	public int getOrder() {
		return OrderConstant.Modifier.SERVICE_CONTRACT_ORDER;
	}
}
