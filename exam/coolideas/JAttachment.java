// Copyright 2022 Julius Schmidt
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import java.io.File;
import java.util.Objects;

public class JAttachment extends JContent {
  private File file;

  public JAttachment(String title, String description, File file) {
    super(title, description);
    this.file = Objects.requireNonNull(file, "file must be non-null");
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    Objects.requireNonNull(file, "file must be non-null");
    this.file = file;
  }

}
