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

package com.jn.sqlhelper.dialect;

import com.jn.langx.util.Emptys;
import com.jn.langx.util.collection.Collects;
import com.jn.langx.util.function.Consumer;

import java.util.List;

public class BaseLikeEscaper implements LikeEscaper {
    public static final BaseLikeEscaper INSTANCE = new BaseLikeEscaper();

    protected static final List<Character> STANDARD_LIKE_KEY_CHARS = Collects.asList(new Character[]{
            '_', '%', '\\'
    });

    @Override
    public List<Character> getLikeKeyChars() {
        return STANDARD_LIKE_KEY_CHARS;
    }

    @Override
    public String escapeLikeKeyChars(String pattern) {
        final List<Character> specifiedChars = getLikeKeyChars();
        if (Emptys.isNotEmpty(pattern)) {
            final StringBuilder builder = new StringBuilder(pattern.length() + 20);
            Collects.forEach(pattern.toCharArray(), new Consumer<Character>() {
                @Override
                public void accept(Character c) {
                    if (specifiedChars.contains(c)) {
                        builder.append(escapeLikeKeyChar(c));
                    } else {
                        builder.append(c);
                    }
                }
            });
            return builder.toString();
        }
        return pattern;
    }

    protected String escapeLikeKeyChar(char c) {
        return "" + c;
    }


    @Override
    public String appentmentAfterLike() {
        return "";
    }
}
