function solution(rows, columns, queries) {
  const answer = [];
  const numArray = [];

  for (let i = 1; i <= rows; i++) {
    const tempArray = [];

    for (let j = 1; j <= columns; j++) {
      tempArray.push((i - 1) * columns + j);
    }

    numArray.push(tempArray);
  }

  for (let i = 0; i < queries.length; i++) {
    const x = queries[i][0] - 1;
    const y = queries[i][1] - 1;
    const xPrime = queries[i][2];
    const yPrime = queries[i][3];

    const temp = numArray[x][y];

    let min = temp;

    for (let j = x; j < xPrime - 1; j++) {
      numArray[j][y] = numArray[j + 1][y];
      min = Math.min(min, numArray[j][y]);
    }

    for (let j = y; j < yPrime - 1; j++) {
      numArray[xPrime - 1][j] = numArray[xPrime - 1][j + 1];
      min = Math.min(min, numArray[xPrime - 1][j]);
    }

    for (let j = xPrime - 1; j > x; j--) {
      numArray[j][yPrime - 1] = numArray[j - 1][yPrime - 1];
      min = Math.min(min, numArray[j][yPrime - 1]);
    }

    for (let j = yPrime - 1; j > y; j--) {
      numArray[x][j] = numArray[x][j - 1];
      min = Math.min(min, numArray[x][j]);
    }

    numArray[x][y + 1] = temp;
    answer.push(min);
  }
  return answer;
}

