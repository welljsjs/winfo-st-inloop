import java.util.ArrayList;
import java.util.List;
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

public class Adapter implements IProject {
  private Project project;

  public Adapter(String name, String description, double rate) {
    project = new Project(name, description, rate);
  }

  @Override
  public void setTask(Task newTask) {
    project.setTask(newTask);
  }

  @Override
  public double getDuration() {
    return project.getDuration();
  }

  @Override
  public long getTotalCost() {
    return project.getTotalCost();
  }

  @Override
  public List<Deliverable> getDeliverables() {
    List<Deliverable> list = new ArrayList<Deliverable>();

    for (List<Deliverable> deliverableList : project.allDeliverables().values()) {
      list.addAll(deliverableList);
    }

    return list;
  }

}