import { describe, expect, test, jest } from "@jest/globals";
import { LoadPlaces } from '@utils/loadTrip';

describe('LoadFile', () => {
    const ExampleJSONObject = '{"places": [{"name": "Jargalant","latitude": "47.20","longitude": "99.16"},{"name": "Milsons Arm Road","latitude": "-32.99","longitude": "151.19"}]}';

    test('base: takes a JSON', async () => {
        const props = {
            setLoadedPlace: jest.fn(),
            setFileValidity: jest.fn(),
            setDisallowLoad: jest.fn(),
            setShowValidityIcon: jest.fn(),
            placeActions: {
                setPlaces: jest.fn()
            },
        };
        await LoadPlaces(props, ExampleJSONObject);
        expect(props.setFileValidity).toHaveBeenCalledWith(true);
    });

    test('base: does not take invalid files', async () => {
        const props = {
            setLoadedPlace: jest.fn(),
            setFileValidity: jest.fn(),
            setDisallowLoad: jest.fn(),
            setShowValidityIcon: jest.fn(),
            placeActions: {
                setPlaces: jest.fn()
            },
        };
        await LoadPlaces(props, "invalid data");
        expect(props.setFileValidity).toHaveBeenCalledWith(false);
    });
});