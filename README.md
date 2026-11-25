# NIFTY OI Monitor

Native Android application for monitoring NIFTY option-chain open interest.

## Features planned

- NIFTY option-chain monitoring
- Nearest weekly expiry
- ATM ±10 strikes
- Five-minute OI snapshots
- PE unwinding early warning
- Confirmed breakdown alerts
- Separate early-warning and confirmation signals

## Current status

This is the first UI prototype.

Live NSE/FYERS data is not connected yet.

## Alert logic

### Early warning
- PE OI decreases
- PE premium increases
- Price approaches support
- Volume provides additional confirmation

### Confirmed breakdown
- NIFTY closes below support on a 5-minute candle
- Volume expansion
- Failed retest can strengthen confirmation

## Security

Broker credentials and access tokens must be stored on a secure backend, never inside the Android APK.

## Build

GitHub Actions automatically builds the debug APK when code is pushed to main.

The APK will be available under Actions → Build Android APK → Artifacts.
