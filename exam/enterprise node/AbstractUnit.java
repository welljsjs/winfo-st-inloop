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
import java.util.Objects;
import java.util.Set;

public abstract class AbstractUnit extends AbstractEnterpriseUnit {
  Set<AbstractEnterpriseUnit> childNodes = new HashSet<AbstractEnterpriseUnit>();

  public AbstractUnit(String name) {
    super(name);
  }

  public boolean add(AbstractEnterpriseUnit childNode) {
    Objects.requireNonNull(childNode, "childNode must be non-null");
    // NOTE I hate instanceof. It's code smell. Why not use polymorphism? Or
    // generics?
    // That's what we get taught anyway.
    if (this instanceof Holding && !(childNode instanceof Company))
      throw new IllegalArgumentException("childNode must be an instance of Company if this is an instance of Holding");
    else if (this instanceof Company && !(childNode instanceof Division))
      throw new IllegalArgumentException("childNode must be an instance of Division if this is an instance of Company");
    else if (this instanceof Division && !(childNode instanceof Team))
      throw new IllegalArgumentException("childNode must be an instance of Team if this is an instance of Division");
    return childNodes.add(childNode);
  }

  public boolean remove(AbstractEnterpriseUnit childNode) {
    Objects.requireNonNull(childNode, "childNode must be non-null");
    return childNodes.remove(childNode);
  }

  public Set<AbstractEnterpriseUnit> getChildNodes() {
    return childNodes;
  }
}
