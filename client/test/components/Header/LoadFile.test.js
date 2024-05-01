import React from 'react';
import '@testing-library/jest-dom';
import { describe, expect, test, jest } from "@jest/globals";
import LoadFile from '@components/Header/LoadFile';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';

describe('LoadFile', () => {
    test('renders without crashing', () => {
      render(<LoadFile />);
      // Since this test doesn't involve interactions or asynchronous behavior,
      // it simply checks if the component renders without throwing any errors.
      // If it renders without any issues, the test will pass.
    });
  });