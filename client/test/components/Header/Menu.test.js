import React from 'react';
import { describe, expect, test, jest, beforeEach } from '@jest/globals';
import user from '@testing-library/user-event';
import { render, screen } from '@testing-library/react';
import Menu from '@components/Header/Menu';

describe('Menu', () => {
    const menuProps = {
        toggleAbout: jest.fn(),
        placeActions: {
            append: jest.fn(),
            removeAll: jest.fn(),
        },
        toggleAddPlace: jest.fn(),
        disableRemoveAll: false,
        toggleSettings: jest.fn(),
        toggleLoadFile: jest.fn(),
        setTripName: jest.fn(),
        places: [{ name: "Example", latitude: "0", longitude: "0" }]
    };

    beforeEach(() => {
        render(<Menu {...menuProps} />);
    });

    // Example test, update others similarly
    test('base: Opens the menu', () => {
        const menuToggle = screen.getByTestId('menu-toggle');
        user.click(menuToggle);
        expect(screen.getByTestId('menu-button-container')).toBeTruthy();
    });

    // Additional tests for interaction behavior
    test('base: Toggles About', async () => {
        const aboutButton = screen.getByTestId('about-button');
        user.click(aboutButton);
        expect(menuProps.toggleAbout).toHaveBeenCalled();
    });
});
