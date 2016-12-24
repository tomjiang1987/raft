/**
 * Copyright (C) 2010-2013 Alibaba Group Holding Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tj.remoting.protocol;

public enum RemotingCommandType {
    REQUEST_COMMAND(0, "request"),
    RESPONSE_COMMAND(1, "response");

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private int type;
    private String description;

    RemotingCommandType(int type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringBuilder(32).append("[type=").append(type).append(",desc=").append(description).append("]").toString();
    }
}