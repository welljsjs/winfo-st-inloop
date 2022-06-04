
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task extends ProjectItem {
  private List<ProjectItem> projectItems = new ArrayList<ProjectItem>();

  public Task(String name, String details, double rate) {
    super(name, details, rate);
  }

  @Override
  public double getTimeRequired() {
    return projectItems.stream().reduce(0.0, (sum, pi) -> sum + pi.getTimeRequired(), Double::sum);
  }

  @Override
  public long getMaterialCost() {
    return projectItems.stream().reduce(0L, (sum, pi) -> sum + pi.getMaterialCost(), Long::sum);
  }

  public void addProjectItem(ProjectItem pi) {
    Objects.requireNonNull(pi, "pi must be non-null");
    System.err.println("before adding, size of projectItems: " + projectItems.size());
    System.err.println("item to add: " + pi);
    projectItems.add(pi);
    System.err.println("after adding, size of projectItems: " + projectItems.size());
  }

  public void removeProjectItem(ProjectItem pi) {
    Objects.requireNonNull(pi, "pi must be non-null");
    projectItems.remove(pi);
  }

  public List<Deliverable> allDeliverables() {
    List<Deliverable> deliverables = new ArrayList<Deliverable>();

    for (ProjectItem pi : projectItems) {
      if (pi instanceof Deliverable)
        deliverables.add((Deliverable) pi);
      else
        deliverables.addAll(((Task) pi).allDeliverables());
    }
    System.err.println("Found " + deliverables.size() + " deliverables.");
    return deliverables;
  }

}