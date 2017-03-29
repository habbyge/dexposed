/*
 * Original work Copyright (c) 2016, Alibaba Mobile Infrastructure (Android) Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taobao.android.dexposed.art.hook;

import com.taobao.android.dexposed.art.method.ArtMethod;

public class OperatorU extends Operator {

    public OperatorU(ArtMethod source) {
        super(source, null);
    }

    public boolean handle(Script script){
        if (script == null)
            return false;

        script.addMethodContext(new MethodContextU(source));
        script.update();
        source.setEntryPointFromQuickCompiledCode(script.getCallHook());
        boolean result = script.activate();
        if (!result) {
            return false;
        }
        return true;
    }
}