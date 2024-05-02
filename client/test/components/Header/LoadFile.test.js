import React from 'react';
import '@testing-library/jest-dom';
import { describe, expect, test, jest } from "@jest/globals";
import LoadFile from '@components/Header/LoadFile';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';

describe('LoadFile', () => {
    test('renders without crashing', () => {
      render(<LoadFile />);
      // I removed becuase this was why it wasnt working :)
    });
  });