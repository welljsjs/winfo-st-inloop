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

import java.util.Objects;

public abstract class Material {
  private String name;
  private double price;

  public Material(String name, double price) {
    Objects.requireNonNull(name, "name must be non-null");
    if (name.isEmpty())
      throw new IllegalArgumentException("name must be non-empty");
    if (price <= 0)
      throw new IllegalArgumentException("price must be positive");

    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public double getPricePerUnit() {
    return price;
  }

  public abstract int getMaterialRequirements(Surface surface);

  public double getPriceOfASurface(Surface surface) {
    Objects.requireNonNull(surface, "surface must be non-null");
    return getMaterialRequirements(surface) * getPricePerUnit();
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Material))
      throw new IllegalArgumentException("obj must be an instance of Material to compare");
    return name.equals(((Material) obj).name);
  }
}