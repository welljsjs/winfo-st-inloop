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

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class StaffMemberIterator implements EnterpriseNodeIterator<StaffMember> {
  private SortedSet<StaffMember> allMembers = new TreeSet<StaffMember>();
  private Iterator<StaffMember> allMembersIterator;

  public StaffMemberIterator(Set<StaffMember> directSubordinates) {
    Objects.requireNonNull(directSubordinates, "directSubordinates must be non-null");

    for (StaffMember directSubordinate : directSubordinates)
      findSubordinatesRecursively(directSubordinate);
    allMembersIterator = allMembers.iterator();
  }

  private void findSubordinatesRecursively(StaffMember m) {
    Objects.requireNonNull(m, "m must be non-null");
    allMembers.add(m);
    for (StaffMember directSubordinate : m.getDirectSubordinates()) {
      if (directSubordinate == null)
        continue;
      allMembers.add(directSubordinate);
      findSubordinatesRecursively(directSubordinate);
    }
  }

  @Override
  public boolean hasNext() {
    return allMembersIterator.hasNext();
  }

  @Override
  public StaffMember next() {
    return allMembersIterator.next();
  }
}