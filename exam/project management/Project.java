import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;

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
// See the License for the   specific language governing permissions and
// limitations under the License.

public class Project {
  private String name;
  private String description;
  private Task mainTask;

  public Project(String name, String description, double rate) {
    Objects.requireNonNull(name, "name must be non-null");
    Objects.requireNonNull(description, "description must be non-null");
    if (name.isEmpty() || description.isEmpty())
      throw new IllegalArgumentException("name and description must be non-empty");
    if (rate < 0)
      throw new IllegalArgumentException("rate must be non-negative");

    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setTask(Task newTask) {
    Objects.requireNonNull(newTask, "newTask must be non-null");
    this.mainTask = newTask;
  }

  public double getDuration() {
    return mainTask.getTimeRequired();
  }

  public long getTotalCost() {
    return mainTask.getCostEstimate();
  }

  public Map<LocalDate, List<Deliverable>> allDeliverables() {
    Objects.requireNonNull(mainTask, "mainTask must be non-null");

    Map<LocalDate, List<Deliverable>> map = new HashMap<LocalDate, List<Deliverable>>();
    for (Deliverable deliverable : mainTask.allDeliverables()) {
      List<Deliverable> currentDeliverables = map.get(deliverable.getDate());
      if (currentDeliverables == null)
        map.put(deliverable.getDate(), new ArrayList<Deliverable>(Arrays.asList(deliverable)));
      else
        currentDeliverables.add(deliverable);

    }
    return map;
  }
}