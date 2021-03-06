/*
 * Licensed to Crate under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.  Crate licenses this file
 * to you under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial
 * agreement.
 */

package io.crate.expression.reference.sys.check.node;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JvmVersionNodeCheckTest {

    @Test
    public void testCheckValidatesSuccessfullyOnJava11() {
        assertThat(JvmVersionNodeCheck.isOnOrAfter11("11.0.2"), is(true));
    }

    @Test
    public void testCheckFailsOnEarlierJavaVersions() {
        assertThat(JvmVersionNodeCheck.isOnOrAfter11("10.0.2"), is(false));
    }

    @Test
    public void testJava8VersionStringCanBeParsed() {
        assertThat(JvmVersionNodeCheck.parseVersion("1.8.0_202"), is(new int[] { 1, 8, 0 }));
    }

    @Test
    public void testJava9VersionStringCanBeParsed() {
        assertThat(JvmVersionNodeCheck.parseVersion("9.0.1"), is(new int[] { 9, 0, 1 }));
    }

    @Test
    public void testJava10VersionStringCanBeParsed() {
        assertThat(JvmVersionNodeCheck.parseVersion("10.0.2"), is(new int[] { 10, 0, 2 }));
    }

    @Test
    public void testJava11VersionStringCanBeParsed() {
        assertThat(JvmVersionNodeCheck.parseVersion("11.0.2"), is(new int[] { 11, 0, 2 }));
    }

    @Test
    public void testEAVersionStringCanBeParsed() {
        assertThat(JvmVersionNodeCheck.parseVersion("12ea"), is(new int[] { 12, 0, 0 }));
    }

    @Test
    public void testEAVersionWithDashCanBeParsed() {
        assertThat(JvmVersionNodeCheck.parseVersion("12-ea"), is(new int[] { 12, 0, 0 }));
    }

    @Test
    public void testSingleVersionNumber() {
        assertThat(JvmVersionNodeCheck.parseVersion("12"), is(new int[] { 12, 0, 0 }));
    }
}
