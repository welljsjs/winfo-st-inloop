// Copyright 2022 Julius Schmidt
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

public class JTopic extends JContent {
  private int id;

  public JTopic(String title, String description, int id) {
    super(title, description);
    if (id < 0)
      throw new IllegalArgumentException("id must be non-negative");
    this.id = id;
  }

  public int getId() {
    return id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof JTopic))
      throw new IllegalArgumentException("obj must be an instance of JTopic to compare");
    return id == ((JTopic) obj).id;
  }
}