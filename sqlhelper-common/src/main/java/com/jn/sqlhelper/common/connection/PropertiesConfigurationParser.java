/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the LGPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at  http://www.gnu.org/licenses/lgpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jn.sqlhelper.common.connection;

import com.jn.langx.util.Throwables;
import com.jn.sqlhelper.langx.configuration.InputStreamConfigurationParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class PropertiesConfigurationParser implements InputStreamConfigurationParser<NamedConnectionConfiguration> {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesConfigurationParser.class);

    @Override
    public NamedConnectionConfiguration parse(InputStream input) {
        try {
            return new NamedConnectionConfiguration(ConnectionConfiguration.loadConfig(input));
        } catch (Throwable ex) {
            throw Throwables.wrapAsRuntimeException(ex);
        }
    }
}
