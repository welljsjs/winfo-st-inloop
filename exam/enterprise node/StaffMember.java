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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class StaffMember implements EnterpriseNode, Comparable<StaffMember> {
  private String name;
  private String job;
  private Set<StaffMember> directSubordinates = new HashSet<StaffMember>();

  public StaffMember(String name, String job) {
    Objects.requireNonNull(name, "name must be non-null");
    Objects.requireNonNull(job, "job must be non-null");
    if (name.isEmpty() || job.isEmpty())
      throw new IllegalArgumentException("name and job must be non-empty");

    this.name = name;
    this.job = job;
  }

  @Override
  public String getName() {
    return name;
  }

  public String getJob() {
    return job;
  }

  public boolean addDirectSubordinate(StaffMember subordinate) {
    Objects.requireNonNull(subordinate, "subordinate must be non-null");
    return directSubordinates.add(subordinate);
  }

  public boolean removeDirectSubordinate(StaffMember subordinate) {
    Objects.requireNonNull(subordinate, "subordinate must be non-null");
    return directSubordinates.remove(subordinate);
  }

  public SortedSet<StaffMember> getDirectSubordinates() { // NOTE WHY on earth does this need to be sorted? Merging two
                                                          // SortedSets (Team.getTeamMembers) requires re-ordering
                                                          // anyway so this makes no sense.
    return new TreeSet<StaffMember>(directSubordinates);
  }

  public String toString() {
    return name;
  }

  @Override
  public int compareTo(StaffMember o) {
    Objects.requireNonNull(o, "o must be non-null");
    return name.compareTo(o.name);
  }

}