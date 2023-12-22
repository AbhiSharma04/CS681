Indian Railway Ticketing System 


Overview:

This Java project simulates a railway ticketing system in two parts: a non-thread-safe version (Part 1) and a thread-safe version (Part 2).

Part 1: Non-Thread-Safe Implementation

Classes: IndianRail, IndianRailwaysTicketingSystem, PassengerRunnable.

Function: Simulates ticket booking without thread safety, prone to race conditions.

Usage: Run PassengerRunnable.main() to see potential race condition issues in the output.

Part 2: Thread-Safe Implementation

Enhancement: Adds ReentrantLock in IndianRail and synchronizes critical sections in IndianRailwaysTicketingSystem.

Function: Resolves race conditions, ensuring accurate seat count updates during concurrent bookings.

Usage: Run PassengerRunnable.main() to observe correct updates in seat counts with concurrent bookings.
