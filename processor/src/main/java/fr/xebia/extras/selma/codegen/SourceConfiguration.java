/*
 * Copyright 2013  Séven Le Mesle
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
 *
 */
package fr.xebia.extras.selma.codegen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class SourceConfiguration {


    private boolean ignoreMissingProperties;
    private boolean ignoreNotSupported;
    private List<String> sourceClass;

    private SourceConfiguration(){}

    public static SourceConfiguration buildFrom(AnnotationWrapper annotationWrapper) {
        SourceConfiguration res = new SourceConfiguration();

        res.ignoreMissingProperties(annotationWrapper.getAsBoolean("ignoreMissingProperties"));
        res.ignoreNotSupported(annotationWrapper.getAsBoolean("ignoreNotSupported"));
        res.sourceClass(annotationWrapper.getAsStrings("withSourceParameter"));
        return res;
    }

    private void sourceClass(List<String> source){
        this.sourceClass = source;
    }

    private SourceConfiguration ignoreNotSupported(boolean b) {
        this.ignoreNotSupported = b;
        return this;
    }

    private SourceConfiguration ignoreMissingProperties(boolean b) {
        this.ignoreMissingProperties = b;
        return this;
    }

    public boolean isIgnoreMissingProperties() {
        return ignoreMissingProperties;
    }

    public boolean isIgnoreNotSupported() {
        return ignoreNotSupported;
    }

    public List<String> getSourceClass() {
        return sourceClass != null ? sourceClass : Collections.EMPTY_LIST;
    }
}
