import { useRef, useEffect } from 'react';

export function useInterval(callback, delay) {
  const callbackRef = useRef();
  let id = useRef();

  if (delay === true) {
    delay = 1000;
  } else {
    delay = null;
  }

  useEffect(() => {
    callbackRef.current = callback;
  }, [callback]);

  useEffect(() => {
    function tick() {
      callbackRef.current();
    }
    if (delay !== null) {
      id.current = setInterval(tick, delay);
    } else {
      return clearInterval(id.current);
    }
  }, [delay]);
}