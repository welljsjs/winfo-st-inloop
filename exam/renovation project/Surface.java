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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Surface extends RenovationObject {
  private double length;
  private double width;
  private Material selectedMaterial;

  public Surface(double length, double width) {
    if (length <= 0 || width <= 0)
      throw new IllegalArgumentException("length and width must be positive");
    this.length = length;
    this.width = width;
  }

  public void setMaterial(Material material) {
    Objects.requireNonNull(material, "material must be non-null");
    selectedMaterial = material;
  }

  public double getArea() {
    return length * width;
  }

  public double getLength() {
    return length;
  }

  public double getWidth() {
    return width;
  }

  @Override
  public double getPrice() {
    return selectedMaterial.getPriceOfASurface(this);
  }

  @Override
  public Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
    Objects.requireNonNull(materials, "materials must be non-null");
    Objects.requireNonNull(selectedMaterial, "selectedMaterial must be non-null");
    if (materials.keySet().stream().anyMatch(Objects::isNull))
      throw new NullPointerException("materials.allKeys must be non-null");
    if (materials.values().stream().anyMatch(Objects::isNull))
      throw new NullPointerException("materials.values must be non-null");

    Map<String, Integer> updatedMaterials = new HashMap<String, Integer>(materials);
    updatedMaterials.merge(selectedMaterial.getName(), selectedMaterial.getMaterialRequirements(this), Integer::sum);

    return updatedMaterials;
  }

}