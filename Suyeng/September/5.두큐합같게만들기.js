class Queue {
  constructor() {
    this.queue = [];
    this.front = 0;
    this.rear = 0;
  }

  enqueue(value) {
    this.queue[this.rear++] = value;
  }

  dequeue() {
    const value = this.queue[this.front];

    delete this.queue[this.front];

    this.front += 1;

    return value;
  }

  peek() {
    return this.queue[this.front];
  }

  size() {
    return this.rear - this.front;
  }
}

function solution(first, second) {
  let answer = 0;

  const sum = (arr) => arr.reduce((a, b) => a + b, 0);

  let queue1Sum = sum(first);
  let queue2Sum = sum(second);

  const total = queue1Sum + queue2Sum;

  if (total % 2 !== 0) {
    return -1;
  }

  const queue1 = new Queue();
  const queue2 = new Queue();

  for (let i = 0; i < first.length; i++) {
    queue1.enqueue(first[i]);
    queue2.enqueue(second[i]);
  }

  console.log(queue1);
  console.log(queue2);

  //만약 큐1의 합이 큐2의 합보다 크다면 queue1을 pop, queue2에 push
  //만약 큐2의 합이 큐1의 합보다 크다면 queue2을 pop, queue1에 push
  //queue 구현 다시해야할듯 ...

  const end = queue1.queue.length * 3;
  let count = 0;

  do {
    if (queue1.sum > queue2.sum) {
      queue2.enqueue(queue1.dequeue());
      console.log(queue2);
    } else if (queue1.sum < queue2.sum) {
      queue1.enqueue(queue2.dequeue());
      console.log(queue1.sum);
    } else {
      break;
    }
  } while (true);
}

const queue1 = [3, 2, 7, 2];
const queue2 = [4, 6, 5, 1];

console.log(solution(queue1, queue2));
