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

public class ResourceType {
  private String description;
  private KeywordCollector collector;

  public ResourceType(String desc, KeywordCollector collector) {
    Objects.requireNonNull(desc, "desc must be non-null");
    Objects.requireNonNull(collector, "collector must be non-null");
    if (desc.isEmpty())
      throw new IllegalArgumentException("desc must be non-null");

    this.description = desc;
    this.collector = collector;
  }

  public String getDescription() {
    return description;
  }

  public KeywordCollector getCollector() {
    return collector;
  }
}