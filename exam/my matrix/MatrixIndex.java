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

public class MatrixIndex {
  private final int row;
  private final int column;

  public MatrixIndex(int row, int column) {
    if (row < 0 || column < 0)
      throw new IllegalArgumentException("row and column must be non-negative");

    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof MatrixIndex))
      throw new IllegalArgumentException("obj must be an instance of MatrixIndex to compare");
    return ((MatrixIndex) obj).getColumn() == column && ((MatrixIndex) obj).getRow() == row;
  }

  @Override
  public int hashCode() {
    final int primeNumber = 31;
    return row * primeNumber + column; // NOTE for O(1) this should be at least commutative.
  }
}
