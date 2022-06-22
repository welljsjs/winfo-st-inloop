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

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class StructuredObject extends RenovationObject {
  private Set<RenovationObject> parts = new HashSet<RenovationObject>();

  public void add(RenovationObject renovationObject) {
    Objects.requireNonNull(renovationObject, "renovationObject must be non-null");
    parts.add(renovationObject);
  }

  @Override
  public double getPrice() {
    return parts.stream().mapToDouble(RenovationObject::getPrice).sum();
  }

  @Override
  public Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
    Objects.requireNonNull(materials, "materials must be non-null");
    if (materials.keySet().stream().anyMatch(Objects::isNull))
      throw new NullPointerException("materials.allKeys must be non-null");
    if (materials.values().stream().anyMatch(Objects::isNull))
      throw new NullPointerException("materials.values must be non-null");

    for (RenovationObject part : parts)
      materials = part.addMaterialRequirements(materials);

    return materials;
  }
}
