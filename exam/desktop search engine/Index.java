import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

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

public class Index {
  private Map<String, List<Resource>> index = new HashMap<String, List<Resource>>();

  public void add(Resource res) {
    Objects.requireNonNull(res, "res must be non-null");
    for (String collectedKeyword : res.getType().getCollector().getKeywords(res))
      index.merge(collectedKeyword, Arrays.asList(res), (u, v) -> Stream.concat(u.stream(), v.stream()).toList());
  }

  public List<Resource> getResources(String keyword) {
    Objects.requireNonNull(keyword, "keyword must be non-null");
    if (keyword.isEmpty())
      throw new IllegalArgumentException("keyword must be non-empty");
    return index.getOrDefault(keyword, new ArrayList<Resource>());
  }
}