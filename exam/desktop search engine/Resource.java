import java.util.Objects;

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

public class Resource {
  private ResourceType rt;
  private String name;
  private String path;

  public Resource(String name, String path, ResourceType rt) {
    Objects.requireNonNull(name, "name must be non-null");
    Objects.requireNonNull(path, "path must be non-null");
    Objects.requireNonNull(rt, "rt must be non-null");
    if (name.isEmpty() || path.isEmpty())
      throw new IllegalArgumentException("name and path must be non-empty");

    this.name = name;
    this.path = path;
    this.rt = rt;
  }

  public String getName() {
    return name;
  }

  public String getPath() {
    return path;
  }

  public ResourceType getType() {
    return rt;
  }
}