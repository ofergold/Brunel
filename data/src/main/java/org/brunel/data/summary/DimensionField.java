/*
 * Copyright (c) 2015 IBM Corporation and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.brunel.data.summary;

import org.brunel.data.Data;
import org.brunel.data.Field;

public class DimensionField implements Comparable<DimensionField> {
    ////// Fields //////////////////////////////////////////////////^n
    public final Field field;
    public final String rename;

    public DimensionField(Field field, String rename) {
        this.field = field;
        this.rename = rename == null ? field.name : rename;
    }

    public int compareTo(DimensionField o) {
        return Data.compare(rename, o.rename);
    }

    public Object getProperty(String key) {
        return field == null ? null : field.getProperty(key);
    }

    public boolean hasProperty(String key) {
        return field != null && field.hasProperty(key);
    }

    public String label() {
        return field == null ? rename : field.label;
    }

    public String toString() {
        return rename.equals(field.name) ? rename : field.name + "[->" + rename + "]";
    }
}
