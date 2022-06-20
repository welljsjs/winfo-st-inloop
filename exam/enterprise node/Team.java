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

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Team extends AbstractEnterpriseUnit {
  private StaffMember teamLeader;

  public Team(String name, StaffMember teamLeader) {
    super(name);
    this.teamLeader = Objects.requireNonNull(teamLeader, "teamLeader must be non-null");
  }

  public StaffMember getTeamLeader() {
    return teamLeader;
  }

  public SortedSet<StaffMember> getTeamMembers() {
    SortedSet<StaffMember> sortedTeamMembers = new TreeSet<StaffMember>();
    sortedTeamMembers.add(teamLeader);
    new StaffMemberIterator(teamLeader.getDirectSubordinates()).forEachRemaining(sortedTeamMembers::add);
    return sortedTeamMembers;
  }
}
