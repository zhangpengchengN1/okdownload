/*
 * Copyright (C) 2017 Jacksgong(jacksgong.com)
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

package cn.dreamtobe.okdownload.core.breakpoint;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BreakpointInfoTest {

    @Test
    public void getPath() {
        BreakpointInfo info = new BreakpointInfo(0, "", "", null);
        assertThat(info.getPath()).isNull();

        final String parentPath = "/sdcard";
        final String filename = "abc";
        info = new BreakpointInfo(0, "", parentPath, filename);
        assertThat(info.getPath()).isEqualTo(new File(parentPath, filename).getAbsolutePath());
    }

    @Test
    public void copyNotClone() {
        BreakpointInfo info = new BreakpointInfo(0, "", "", null);
        info.addBlock(new BlockInfo(0, 0));

        final BreakpointInfo copy = info.copy();

        assertThat(info.getBlock(0)).isNotEqualTo(copy.getBlock(0));
    }

    @Test
    public void getTotalLength() {
        BreakpointInfo info = new BreakpointInfo(0, "", "", null);
        info.addBlock(new BlockInfo(0, 10));
        info.addBlock(new BlockInfo(10, 18, 2));
        info.addBlock(new BlockInfo(28, 66, 20));

        assertThat(info.getTotalLength()).isEqualTo(94);
    }
}