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

public abstract class Part {
  private String id;
  private String name;

  public Part(String id, String name) {
    Objects.requireNonNull(id, "id must be non-null");
    Objects.requireNonNull(name, "name must be non-null");
    if (id.isEmpty())
      throw new IllegalArgumentException("id must be non-empty");
    if (name.isEmpty())
      throw new IllegalArgumentException("name must be non-empty");

    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}